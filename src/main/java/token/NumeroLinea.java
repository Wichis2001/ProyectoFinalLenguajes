/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package token;

/**
 * Esta clase me permite establecerle un número de linea a un JTextArea para poder determinar el lugar donde estamos
 * @author luis
 */
import java.awt.*;
import java.beans.*;
import java.util.HashMap;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import javax.swing.text.*;

/**
 * Esta clase me permite establecer los elementos necesarios para poder asociar este cambio de linia
 * @author luis
 */
public class NumeroLinea extends JPanel implements CaretListener, DocumentListener, PropertyChangeListener {

    /**
     * Me permite establecer una variable estatica que estara asociada a la izquierda
     */
    public final static float LEFT = 0.0f;

    /**
     * Me permite establecer una variable estatica que estara asociada al centro
     */
    public final static float CENTER = 0.5f;

    /**
     * Me permite establecer una variable estatica que estara asociada ala derecha
     */
    public final static float RIGHT = 1.0f;
    private Color color1 = new Color(7,86,100);
    private final static Border OUTER = new MatteBorder(0, 0, 0, 2,Color.BLACK);

    private final static int HEIGHT = Integer.MAX_VALUE - 1000000;

    private JTextComponent component;

    private boolean updateFont;
    private int borderGap;
    private Color currentLineForeground;
    private float digitAlignment;
    private int minimumDisplayDigits;

    
    private int lastDigits;
    private int lastHeight;
    private int lastLine;

    private HashMap<String, FontMetrics> fonts;

    /**
     * Este constructor me permite asociar un nuevo numero de linea a travez de un JTextArea Asociado
     * @param component
     */
    public NumeroLinea(JTextComponent component) {
        this(component, 3);
    }

    /**
     * Este constructor me permite establecer un nuevo número de linea a travez de un JTextArea y el minimo de digitos que peude haber en pantalla
     * @param component
     * @param minimumDisplayDigits
     */
    public NumeroLinea(JTextComponent component, int minimumDisplayDigits) {
        this.component = component;

        setFont(component.getFont());

        setBorderGap(5);
        setCurrentLineForeground(color1);
        setDigitAlignment(RIGHT);
        setMinimumDisplayDigits(minimumDisplayDigits);

        component.getDocument().addDocumentListener(this);
        component.addCaretListener(this);
        component.addPropertyChangeListener("font", this);
    }

    /**
     * Este metodo me devuelve una actualizacion de la fuente
     * @return
     */
    public boolean getUpdateFont() {
        return updateFont;
    }

    /**
     * Este metodo me permite cambiar la actualizacion de la fuente
     * @param updateFont
     */
    public void setUpdateFont(boolean updateFont) {
        this.updateFont = updateFont;
    }

    /**
     * Este metdo me devuelve el botde
     * @return
     */
    public int getBorderGap() {
        return borderGap;
    }

    /**
     * Este metodo me permite cambiar el borde
     * @param borderGap
     */
    public void setBorderGap(int borderGap) {
        this.borderGap = borderGap;
        Border inner = new EmptyBorder(0, borderGap, 0, borderGap);
        setBorder(new CompoundBorder(OUTER, inner));
        lastDigits = 0;
        setPreferredWidth();
    }

    /**
     * Este metodo me devuelve el color que esta asociado a la linea de texto
     * @return
     */
    public Color getCurrentLineForeground() {
        return currentLineForeground == null ? getForeground() : currentLineForeground;
    }

    /**
     * Este metodo me permite cambiar la linea de texto
     * @param currentLineForeground
     */
    public void setCurrentLineForeground(Color currentLineForeground) {
        this.currentLineForeground = currentLineForeground;
    }

    /**
     * Este metodo me devuelve los alineamientos de los digitos
     * @return
     */
    public float getDigitAlignment() {
        return digitAlignment;
    }

    /**
     * Este metodo me permite cambiar los alineamientos de los digitos de mi texto de area
     * @param digitAlignment
     */
    public void setDigitAlignment(float digitAlignment) {
        this.digitAlignment
                = digitAlignment > 1.0f ? 1.0f : digitAlignment < 0.0f ? -1.0f : digitAlignment;
    }

    /**
     * Este metodo me devuelve el nomero minimo de digitos que se muestra en pantalla
     * @return
     */
    public int getMinimumDisplayDigits() {
        return minimumDisplayDigits;
    }

    /**
     * Este metodo me permite cambiar el minimo de digitos que se muestra en pantalla
     * @param minimumDisplayDigits
     */
    public void setMinimumDisplayDigits(int minimumDisplayDigits) {
        this.minimumDisplayDigits = minimumDisplayDigits;
        setPreferredWidth();
    }

    
    private void setPreferredWidth() {
        Element root = component.getDocument().getDefaultRootElement();
        int lines = root.getElementCount();
        int digits = Math.max(String.valueOf(lines).length(), minimumDisplayDigits);

        if (lastDigits != digits) {
            lastDigits = digits;
            FontMetrics fontMetrics = getFontMetrics(getFont());
            int width = fontMetrics.charWidth('0') * digits;
            Insets insets = getInsets();
            int preferredWidth = insets.left + insets.right + width;

            Dimension d = getPreferredSize();
            d.setSize(preferredWidth, HEIGHT);
            setPreferredSize(d);
            setSize(d);
        }
    }

    /**
     * Este metodo se encarga de pintar mi componente en el text area
     * @param g
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        FontMetrics fontMetrics = component.getFontMetrics(component.getFont());
        Insets insets = getInsets();
        int availableWidth = getSize().width - insets.left - insets.right;

        Rectangle clip = g.getClipBounds();
        int rowStartOffset = component.viewToModel(new Point(0, clip.y));
        int endOffset = component.viewToModel(new Point(0, clip.y + clip.height));

        while (rowStartOffset <= endOffset) {
            try {
                if (isCurrentLine(rowStartOffset)) {
                    g.setColor(getCurrentLineForeground());
                } else {
                    g.setColor(getForeground());
                }

                String lineNumber = getTextLineNumber(rowStartOffset);
                int stringWidth = fontMetrics.stringWidth(lineNumber);
                int x = getOffsetX(availableWidth, stringWidth) + insets.left;
                int y = getOffsetY(rowStartOffset, fontMetrics);
                g.drawString(lineNumber, x, y);
                rowStartOffset = Utilities.getRowEnd(component, rowStartOffset) + 1;
            } catch (Exception e) {
                break;
            }
        }
    }

    private boolean isCurrentLine(int rowStartOffset) {
        int caretPosition = component.getCaretPosition();
        Element root = component.getDocument().getDefaultRootElement();

        if (root.getElementIndex(rowStartOffset) == root.getElementIndex(caretPosition)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Este metodo me deuelve el numero de linea que esta asociado al text area
     * @param rowStartOffset
     * @return
     */
    protected String getTextLineNumber(int rowStartOffset) {
        Element root = component.getDocument().getDefaultRootElement();
        int index = root.getElementIndex(rowStartOffset);
        Element line = root.getElement(index);

        if (line.getStartOffset() == rowStartOffset) {
            return String.valueOf(index + 1);
        } else {
            return "";
        }
    }

    /*
	 *  Determine the X offset to properly align the line number when drawn
     */
    private int getOffsetX(int availableWidth, int stringWidth) {
        return (int) ((availableWidth - stringWidth) * digitAlignment);
    }

    /*
	 *  Determine the Y offset for the current row
     */
    private int getOffsetY(int rowStartOffset, FontMetrics fontMetrics)
            throws BadLocationException {
        //  Get the bounding rectangle of the row

        Rectangle r = component.modelToView(rowStartOffset);
        int lineHeight = fontMetrics.getHeight();
        int y = r.y + r.height;
        int descent = 0;

        //  The text needs to be positioned above the bottom of the bounding
        //  rectangle based on the descent of the font(s) contained on the row.
        if (r.height == lineHeight) // default font is being used
        {
            descent = fontMetrics.getDescent();
        } else // We need to check all the attributes for font changes
        {
            if (fonts == null) {
                fonts = new HashMap<String, FontMetrics>();
            }

            Element root = component.getDocument().getDefaultRootElement();
            int index = root.getElementIndex(rowStartOffset);
            Element line = root.getElement(index);

            for (int i = 0; i < line.getElementCount(); i++) {
                Element child = line.getElement(i);
                AttributeSet as = child.getAttributes();
                String fontFamily = (String) as.getAttribute(StyleConstants.FontFamily);
                Integer fontSize = (Integer) as.getAttribute(StyleConstants.FontSize);
                String key = fontFamily + fontSize;

                FontMetrics fm = fonts.get(key);

                if (fm == null) {
                    Font font = new Font(fontFamily, Font.PLAIN, fontSize);
                    fm = component.getFontMetrics(font);
                    fonts.put(key, fm);
                }

                descent = Math.max(descent, fm.getDescent());
            }
        }

        return y - descent;
    }

//
//  Implement CaretListener interface
//

    /**
     * Este metodo me permite hacer una actualizacion de los datos
     * @param e
     */
    @Override
    public void caretUpdate(CaretEvent e) {
        //  Get the line the caret is positioned on

        int caretPosition = component.getCaretPosition();
        Element root = component.getDocument().getDefaultRootElement();
        int currentLine = root.getElementIndex(caretPosition);

        //  Need to repaint so the correct line number can be highlighted
        if (lastLine != currentLine) {
            repaint();
            lastLine = currentLine;
        }
    }

//
//  Implement DocumentListener interface
//

    /**
     * Este metodo me permite hacer un cambio a mi actualizacion de datos
     * @param e
     */
    @Override
    public void changedUpdate(DocumentEvent e) {
        documentChanged();
    }

    /**
     * Este metodo me permite insertar una actualizacion a mi documento
     * @param e
     */
    @Override
    public void insertUpdate(DocumentEvent e) {
        documentChanged();
    }

    /**
     * Este metodo me permite remover una actualizacion de un documento
     * @param e
     */
    @Override
    public void removeUpdate(DocumentEvent e) {
        documentChanged();
    }

    /*
	 *  A document change may affect the number of displayed lines of text.
	 *  Therefore the lines numbers will also change.
     */
    private void documentChanged() {
        //  View of the component has not been updated at the time
        //  the DocumentEvent is fired

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    int endPos = component.getDocument().getLength();
                    Rectangle rect = component.modelToView(endPos);

                    if (rect != null && rect.y != lastHeight) {
                        setPreferredWidth();
                        repaint();
                        lastHeight = rect.y;
                    }
                } catch (BadLocationException ex) {
                    /* nothing to do */ }
            }
        });
    }

    /**
     * Este metodo me permite alterar la propiedad que esta asociada a la fuente
     * @param evt
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getNewValue() instanceof Font) {
            if (updateFont) {
                Font newFont = (Font) evt.getNewValue();
                setFont(newFont);
                lastDigits = 0;
                setPreferredWidth();
            } else {
                repaint();
            }
        }
    }
}
