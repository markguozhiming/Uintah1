//**************************************************************************
// Program : IntegerField.java
// Purpose : An extension of JTextField to take care of integers
// Author  : Biswajit Banerjee
// Date    : 12/7/1998
// Mods    :
//**************************************************************************

//************ IMPORTS **************
import java.text.NumberFormat;
import java.text.ParseException;
import javax.swing.*;
import javax.swing.text.*;

//**************************************************************************
// Class   : IntegerField
// Purpose : Creates a text field that validates integers
//**************************************************************************
public class IntegerField extends JTextField {

  // Data
  private NumberFormat integerFormatter;

  // Data that may be needed later

  // Constructor
  public IntegerField(int value, int columns) {
    
    // Set the size of the component
    super(columns);
    
    // Get the toolkit
    integerFormatter = NumberFormat.getNumberInstance();
    integerFormatter.setParseIntegerOnly(true);
    setValue(value);
  }

  // Get method
  public int getValue() {
    int retVal = 0;
    try {
      retVal = integerFormatter.parse(getText()).intValue();
    } catch (ParseException e) {
    }
    return retVal;
  }

  // set method
  public void setValue(int value) {
    setText(integerFormatter.format(value));
  }

  // Create the related document
  protected Document createDefaultModel() {
    return new IntegerDocument();
  }

  // Inner class for integer document
  protected class IntegerDocument extends PlainDocument {

    // The insert string method
    public void insertString(int offs, String src, AttributeSet a)
      throws BadLocationException {

      char[] source = src.toCharArray();
      char[] result = new char[source.length];
      int j = 0;

      for (int i = 0; i < result.length; i++) {
	if (Character.isDigit(source[i])) result[j++] = source[i];
      }
      super.insertString(offs, new String(result,0,j), a);
    }
  }
}
