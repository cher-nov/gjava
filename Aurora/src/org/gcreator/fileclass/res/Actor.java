/*
 * Copyright (C) 2007-2008 Luís Reis <luiscubal@gmail.com>
 * Copyright (C) 2007-2008 TGMG <thegamemakerguru@hotmail.com>
 * Copyright (C) 2008 Serge Humphrey <bob@bobtheblueberry.com>
 * 
 * This file is part of G-Creator.
 * G-Creator is free software and comes with ABSOLUTELY NO WARRANTY.
 * See LICENSE for more details.
 */
package org.gcreator.fileclass.res;

import java.util.Enumeration;
import java.util.Vector;
import org.gcreator.actions.Action;
import org.gcreator.components.ActorFieldPane.Field;
import org.gcreator.events.Event;
import org.gcreator.fileclass.GFile;

/**
 *
 * @author TGMG
 */
public class Actor implements Resource {

    static final long serialVersionUID = 1L;
    public boolean visible = true,  solid = true,  persistent;
    public GFile sprite;
    public GFile extend,  mask;
    public int index,  depth;
    public Vector<Event> events;
    public Vector<Field> fields;
    
    public Actor() {
        events = new Vector<Event>();
        fields = new Vector<Field>();
    }

    public String writeXml() {
        String xml = "";
        xml += "<?xml version=\"1.0\"?>\n"; //changes for new version of actor
        xml += "<actoreditor version=\"1.0\"/>\n";
        xml += "<Solid>" + solid + "</Solid>\n";
        xml += "<Visible>" + visible + "</Visible>\n";
        xml += "<Persistent>" + persistent + "</Persistent>\n";
        if (sprite == null) {
            xml += "<Sprite>//!NULLSPRITE</Sprite>";
        } else {
            xml += "<Sprite>" + sprite + "</Sprite>";
        }
        for (Enumeration e = events.elements(); e.hasMoreElements();) {
            xml += ((Event) e.nextElement()).writeXml();
        }
        return xml;
    }

    // <editor-fold desc="Useless Junk" defaultstate="collapsed">
//    public void readXml(String xml) {
//        //TODO read the XML
//        String[] data = xml.split("\n");
//        String version = "1.0"; //guess
//        for (int i = 0; i < data.length; i++) {
//            if (data[i].indexOf("<actoreditor") !=-1) {
//                version = data[i].replaceAll("<actoreditor version=\\\"", "").replaceAll("\\\"/>", "");
//                if (!version.equals("1.0")) {
//                    System.out.println("Warning! Version is" + version);
//                }
//            } else if (data[i].indexOf("<Solid>") !=-1) {
//                solid = Boolean.parseBoolean(data[i].replaceAll("<Solid>", "").replaceAll("</Solid>", ""));
//            } else if (data[i].contains("<Visible>")) {
//                visible = Boolean.parseBoolean(data[i].replaceAll("<Visible>", "").replaceAll("</Visible>", ""));
//            } else if (data[i].contains("<Persistant>")) {
//                persistant = Boolean.parseBoolean(data[i].replaceAll("<Persistant>", "").replaceAll("</Persistant>", ""));
//            } else if (data[i].contains("<event")) {
//                String n = data[i].replaceAll("<event type=\"(.*?)\">", "$1");
//                System.out.println("Event " + n);
//                try {
//                    Event evt;
//                    events.add(evt = Event.getNewEventFromName(n));
//                    i = evt.readXml(data, i);
//                } catch (Exception e) {
//                }
//            }
//        }
//    }
    /*    public String exportToHtml(boolean xhtml) {
    String html = "";
    if (xhtml) {
    html += "<!DOCTYPE html\nPUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\"\n\"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">";
    } else {
    html += "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\"\n\"http://www.w3.org/TR/html4/loose.dtd\">";
    }
    html += "\n";
    html += "<html>\n";
    html += "<head>\n";
    html += "\t<title>" + name + "</title>\n";
    html += "</head>\n";
    html += "<body>\n";
    html += "<table border=";
    if (xhtml) {
    html += "\"";
    }
    html += "1";
    if (xhtml) {
    html += "\"";
    }
    html += " width=";
    if (xhtml) {
    html += "\"";
    }
    html += "100%";
    if (xhtml) {
    html += "\"";
    }
    html += ">\n";
    html += "<tr>\n";
    html += "<td>\n";
    html += "Name: " + name + "<br";
    if (xhtml) {
    html += "/";
    }
    html += ">\n";
    html += "<fieldset>\n<legend>Sprite</legend>\n</fieldset>\n";
    html += "<input type=\"checkbox\"";
    if (visible) {
    html += " checked";
    if (xhtml) {
    html += "=\"checked\"";
    }
    }
    html += " disabled";
    if (xhtml) {
    html += "=\"disabled\" /";
    }
    html += ">Visible<br";
    if (xhtml) {
    html += "/";
    }
    html += ">\n";
    html += "<input type=\"checkbox\"";
    if (solid) {
    html += " checked";
    if (xhtml) {
    html += "=\"checked\"";
    }
    }
    html += " disabled";
    if (xhtml) {
    html += "=\"disabled\" /";
    }
    html += ">Solid<br";
    if (xhtml) {
    html += "/";
    }
    html += ">\n";
    html += "<input type=\"checkbox\"";
    if (persistant) {
    html += " checked";
    if (xhtml) {
    html += "=\"checked\"";
    }
    }
    html += " disabled";
    if (xhtml) {
    html += "=\"disabled\" /";
    }
    html += ">Persistant<br";
    if (xhtml) {
    html += "/";
    }
    html += ">\n";
    if (xhtml) {
    html += "</td>\n";
    }
    if (xhtml) {
    html += "</tr>\n";
    }
    html += "</table>\n";
    html += "</body>\n";
    html += "</html>";
    return html;
    }*/
// </editor-fold>

    @Override
    @SuppressWarnings("unchecked")
    public Object clone() {
        Actor a = new Actor();
        a.depth = depth;
        Vector ev = new Vector();
        for (Event e : events) {
            ev.add(e);
            e.actions = (Vector<Action>) e.actions.clone();
        }
        a.events = ev;
        a.extend = extend;
        a.index = index;
        a.mask = mask;
        a.persistent = persistent;
        a.solid = solid;
        a.visible = visible;
        a.sprite = sprite;
        a.fields = (Vector<Field>) fields.clone();
        return a;
    }

    public void readXml(String xml) {}
}
