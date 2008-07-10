/*
 * Copyright (C) 2007-2008 Luís Reis <luiscubal@gmail.com>
 * Copyright (C) 2007-2008 TGMG <thegamemakerguru@hotmail.com>
 * Copyright (c) 2008 BobSerge or Bobistaken <serge_1994@hotmail.com>
 * 
 * This file is part of G-Creator.
 * G-Creator is free software and comes with ABSOLUTELY NO WARRANTY.
 * See LICENSE for more details.
 */
package org.gcreator.fileclass;

import java.io.Serializable;
import org.gcreator.exceptions.*;
import java.util.*;
import javax.swing.ImageIcon;

/**
 *
 * @author Luís
 */
public class Folder extends GObject implements Serializable{
    protected Vector<GObject> childNodes;
    static final long serialVersionUID = 1L;
    //used for serialize
    public Folder(){}
    
    public Folder(String name){
        super(name);
        childNodes = new Vector<GObject>(); //Up to 100 childNodes
    }
    
    public Vector getChildren(){
        return childNodes;
    }
    
    public boolean add(GObject node){
        int i = 0;
        return childNodes.add(node);
    }
    
    public Folder magicAddition(GFile file)
    {
        return magicAddition(file.type);
    }
    
    public Folder magicAddition(String type)
    {
        if(allowsFileType(type))
            return this;
        return null;
    }
    
    public Folder magicAddition(Group folder){
        if(allowsGroup(folder))
            return this;
        return null;
    }
    
    public int find(GObject search){
        if(childNodes==null)
            return -1;
        for(int i = 0; i < childNodes.size(); i++)
            if(childNodes.get(i)==search)
                return i;
        return -1;
    }
    
    public int findFromName(String search){
        if(childNodes==null)
            return -1;
        for(int i = 0; i < childNodes.size(); i++)
            if(childNodes.get(i)!=null&&childNodes.get(i).name.equals(search))
                return i;
        return -1;
    }
    
    public GObject childAt(int pos){
        return childNodes.get(pos);
    }
    
    public int getChildNum(){
        int num = 0;
        for(int i = 0; i < childNodes.size(); i++)
            if(childNodes.get(i)!=null)
                num++;
        return num;
    }
    
    public int getChildArrayNum(){
        return childNodes.size();
    }
    
    public boolean allowsFileType(String format){
        return true;
    }
    
    public boolean allowsGroup(Group group){
        return true;
    }
    
    public boolean allowsDelete(GObject o){
        return true;
    }
    
    public void remove(GObject o){
        childNodes.remove(o);
    }
    
    public Group newGroup(String name){
        Group group = new Group(this, name);
        add(group);
        return group;
    }
    
    public Folder findFolder(String name) throws NoSuchFolderException{
        if(name==null)
            throw new NoSuchFolderException("null folder name");
        if(name.equals("")||name.equals("/"))
            return this;
        if(name.charAt(0)=='/')
            name = name.substring(1);
        if(name.indexOf("/")==-1){
            if(!name.equals(this.name))
                throw new NoSuchFolderException("Obtained name is " + name +
                        " but expected name was " + this.name);
            else
                return this;
        }
        else
            if(!name.substring(name.indexOf("/")).equals(name))
                throw new NoSuchFolderException();
        for(int i = 0; i < childNodes.size(); i++){
            GObject o = childNodes.get(i);
            if(o != null && o instanceof Folder){
                try{
                    Folder a = ((Folder) o).findFolder(name.substring(name.indexOf(name)));
                    return a;
                }
                catch(NoSuchFolderException e){}
            }
        }
        throw new NoSuchFolderException("Folder");
    }
    
    public Folder findChildFolder(String name) throws NoSuchFolderException{
        for(GObject o : childNodes){
            if(o instanceof Folder&&((Folder) o).name.equals(name))
                return (Folder) o;
        }
        throw new NoSuchFolderException("No folder with given name");
    }
    
     
    public String getObjectType(){
        return "Folder";
    }
    
    public ImageIcon getImage(){
        return null;
    }
}
