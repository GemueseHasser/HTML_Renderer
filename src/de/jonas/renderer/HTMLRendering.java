package de.jonas.renderer;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class HTMLRendering extends JFrame {

    public static boolean Edit = false;
    public static boolean Offline = true;
    public static String WebAdresse = null;

    private static final String TITLE_STRING = "HTML Rendering";

    private static String URL = null;

    private static final String VERSION_STRING = "1.0";

    // --------------------------- main() method ---------------------------
    @SuppressWarnings( { "UnusedParameters" } )
    public HTMLRendering() {
        if(Offline == true) {
            URL = "file:///" + openFrom(null);
        } else if(Offline == false) {
            URL = WebAdresse;
        }
        System.out.println(URL);
        SwingUtilities.invokeLater(new Runnable() {
            public void run()
            {
                System.out.println( "Starting" );
                final JFrame jframe =
                        new JFrame( TITLE_STRING + " " + VERSION_STRING );
                Container contentPane = jframe.getContentPane();
                jframe.setExtendedState(JFrame.MAXIMIZED_BOTH);

                contentPane.setBackground( Color.YELLOW );
                contentPane.setForeground( Color.BLUE );
                jframe.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
                try
                {
                    System.out.println( "acquiring URL" );
                    JEditorPane jep = new JEditorPane( URL );
                    System.out.println( "URL acquired" );
                    jep.setContentType("text/html");
                    jep.setEditable(Edit);
                    JScrollPane jsp =
                            new JScrollPane( jep,
                                    JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                                    JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED );
                    contentPane.add( jsp );
                }
                catch (IOException ex) {
                    ex.printStackTrace();
                    contentPane.add( new JLabel( "URL wurde nicht gefunden xD", JLabel.CENTER ) );
                }
                jframe.validate();
                jframe.setVisible( true );
            }
        } );
    }

    public String openFrom(String pfad) {
        JFileChooser chooser;
        if (pfad == null)
            pfad = System.getProperty("user.home");
        File file = new File(pfad.trim());

        chooser = new JFileChooser(pfad);
        chooser.setDialogType(JFileChooser.SAVE_DIALOG);
        FileNameExtensionFilter markUpFilter = new FileNameExtensionFilter(
                "Markup: html", "html");
        chooser.removeChoosableFileFilter(chooser.getAcceptAllFileFilter());
        chooser.setFileFilter(markUpFilter);
        chooser.setDialogTitle("Öffnen...");
        chooser.setVisible(true);

        int result = chooser.showOpenDialog(this);

        if (result == JFileChooser.OPEN_DIALOG) {

            pfad = chooser.getSelectedFile().toString();
            file = new File(pfad);
            if (markUpFilter.accept(file))
                System.out.println(pfad + " wurde geöffnet.");
            else
                System.out.println(pfad + " ist der falsche Dateityp.");

            chooser.setVisible(false);
            return pfad;
        }
        chooser.setVisible(false);
        return null;
    }
}
