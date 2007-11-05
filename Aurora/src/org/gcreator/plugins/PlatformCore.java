/*
 * PlatformCore.java
 *
 * Created on 07-Sep-2007, 19:34:25
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.gcreator.plugins;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.swing.ImageIcon;
import org.antlr.runtime.ANTLRFileStream;
import org.antlr.runtime.CommonTokenStream;
import org.gcreator.fileclass.Folder;
import org.gcreator.fileclass.Project;
import org.gcreator.fileclass.res.Actor;
import org.gcreator.fileclass.res.Scene;
import org.gcreator.fileclass.res.Sprite;
import org.gcreator.plugins.platform.gscriptLexer;
import org.gcreator.plugins.platform.gscriptParser;

/**
 *
 * @author Ali1
 */
public class PlatformCore extends PluginCore {

    public String returncode = "";

    public void putFolder(Folder folder) {
        org.gcreator.fileclass.Object childNode;


        for (int i = 0; i < folder.getChildArrayNum(); i++) {
            if ((childNode = folder.childAt(i)) != null) {
                if (childNode instanceof org.gcreator.fileclass.File) {
                    try {
                        if (((org.gcreator.fileclass.File) childNode).type.equals("sprite")) {
                            parseSprite((Sprite) ((org.gcreator.fileclass.File) childNode).value);
                        } else if (((org.gcreator.fileclass.File) childNode).type.equals("actor")) {
                            parseActor((Actor) ((org.gcreator.fileclass.File) childNode).value);
                        } else if (((org.gcreator.fileclass.File) childNode).type.equals("scene")) {
                            parseScene((Scene) ((org.gcreator.fileclass.File) childNode).value);
                        } else if (((org.gcreator.fileclass.File) childNode).type.equals("jpg")) {
                            parseImage((ImageIcon) ((org.gcreator.fileclass.File) childNode).value, (org.gcreator.fileclass.File) childNode);
                        } else if (((org.gcreator.fileclass.File) childNode).type.equals("png")) {
                            parseImage((ImageIcon) ((org.gcreator.fileclass.File) childNode).value, (org.gcreator.fileclass.File) childNode);
                        } else if (((org.gcreator.fileclass.File) childNode).type.equals("gif")) {
                            parseImage((ImageIcon) ((org.gcreator.fileclass.File) childNode).value, (org.gcreator.fileclass.File) childNode);
                        } else if (((org.gcreator.fileclass.File) childNode).type.equals("egml")) {
                            parseClass((String) ((org.gcreator.fileclass.File) childNode).value);
 }
                    } catch (Exception e) {
                    }
                } else if (childNode instanceof org.gcreator.fileclass.Folder) {
                    putFolder((Folder) childNode);
                }
            }
        }
    }

    public void parseSprite(Sprite s) {
        System.out.println("" + s.name);
    }

    public void parseImage(ImageIcon i, org.gcreator.fileclass.File f) {
    }

    public void parseActor(Actor a) throws IOException {
    }

    public void parseScene(Scene s) throws IOException {
    }

    public void parseClass(String s) throws IOException {
    }

    /**
     * Varstatement called when a varstatement is parsed, returns the language code
     * @param type - type of variable
     * @param vars
     * @return the language code for this statement
     */
    public String varstatement(String type, String vars) {
        System.out.println("Var statement: " + type + vars);
        return type + " "+vars+";";
    }

    public String fieldstatement(String m, String varstatement) {
        return ";";
    }

    public String returnstatement(String exp) {
        return "return " + exp + ";";
    }

    public String exitstatement() {
        return "return;";
    }

    public String ifstatement(String exp, String statement, String elses) {
        return "if (" + exp + ") \n" + statement + " \n " + elses;
    }

    public String elsestatement(String statement) {
        return " else " + statement;
    }
    
    public String bstatement(String st) {
        return "{ \n"+st+"\n }";
    }

    public String expression(String ex) {
        return ex;
    }
    
    public String notexpression(String exp) {
        return " !("+exp+")";
    }
    
    public String pexpression(String exp) {
        return " ("+exp+")";
    }
    
    public String andexpression() {
        return " && ";
    }
    
    public String orexpression() {
        return " || ";
    }
    
    public String xorexpression() {
        return " ^^ ";
    }
    
    public String repeatstatement(String ex, String st) {
        return ""; //todo
    }
    
    public String breakstatement() {
        return "break;";
    }
    
    public String continuestatement() {
        return "continue;";
    }
    
    public String dostatement(String st, String exp) {
        return ""; //todo
    }
    
    public String whilestatement(String exp, String st) {
        return "while ("+exp + ") "+ st;
    }
    
    public String forstatement(String statement1, String exp, String statement2, String statements) {
        return "for ("+statement1+"; "+exp+"; "+statement2+") "+statements;
    }
    
    public String switchstatement() {
        return ""; //TODO
    }    
    
    public String withstatement(String exp, String statement) {
        return "with " + exp + " " + statement;
    }    
    
    public String assignmentstatement(String variable, String operator, String expression) {
        return variable+" "+operator+" "+expression;
    }
    
    public String functionstatement(String name, String parameters) {
        return name+ "("+parameters+")";
    }
    
    public String otherclassfunctionstatement(String name, String parameters) {
        return name+ "("+parameters+")";
    }

    public String array(String name, String exp) {
        return name + "[" + exp + "]";
    }

    public String parseGCL(String code, PlatformCore p) throws IOException {
        //change code simply for testing
        gscriptParser parser;
        gscriptLexer lex = null;

        code = "int i; int ii; int iii; { me = 3; if (5==2) {}} /* hey */  return 8;";

        FileWriter ftempcode = new FileWriter("tempcode.gcl");
        BufferedWriter tempcode = new BufferedWriter(ftempcode);
        tempcode.write(code);
        tempcode.close();
        System.out.println("test");
        lex = new gscriptLexer(new ANTLRFileStream(new File("tempcode.gcl").getAbsolutePath()));
        CommonTokenStream tokens = new CommonTokenStream(lex);

        parser = new gscriptParser(tokens);
        parser.setPlatform(p);
        try {
            parser.code();
            System.out.println("Finished! Code output:"+this.returncode);
        } catch (Exception e) {
            System.out.println("Error:" + e.getLocalizedMessage() + " " + e.getMessage());
        }
        return "";
    }

    public void run(Project project) {
        if (project != null) {
            putFolder(project);
        }
    }

    // Print a line to the bufferedwriter
    public static void print(BufferedWriter file, String printString) throws IOException {
        file.write(printString);
        file.newLine();
    }

    public static void openbrowser(String location) {
        if (System.getProperty("os.name").indexOf("Windows") == 0) {
            try {
                Runtime.getRuntime().exec("explorer.exe " + location);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            // Unsupported OS for opening the browser
        }
    }

    public void copyDirectory(File srcDir, File dstDir) throws IOException {
        if (srcDir.isDirectory()) {
            if (!dstDir.exists()) {
                dstDir.mkdir();
            }

            String[] children = srcDir.list();
            for (int i = 0; i < children.length; i++) {
                copyDirectory(new File(srcDir, children[i]), new File(dstDir, children[i]));
            }
        } else {
            copyFile(srcDir, dstDir);
        }
    }

    // Copies src file to dst file.
    // If the dst file does not exist, it is created
    void copyFile(File src, File dst) throws IOException {
        InputStream in = new FileInputStream(src);
        OutputStream out = new FileOutputStream(dst);

        // Transfer bytes from in to out
        byte[] buf = new byte[1024];
        int len;
        while ((len = in.read(buf)) > 0) {
            out.write(buf, 0, len);
        }
        in.close();
        out.close();
    }
}