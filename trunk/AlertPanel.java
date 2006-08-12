/***********************************************************
* File:	AlertPanel.java
*
* @author 		Anupom (http://anupom.wordpress.com)
*
* @description 	The panel that contains all the components of a
*				DesktopAlert. Uses gradient in the background.
*
* @version 0.01 August 11,2006
*
***********************************************************/

import java.awt.*;
import javax.swing.*;

public class AlertPanel extends JPanel
{
	//upper & lower gradient color of the panel
	Color color1,color2;
	// description text that appears inside the panel
	String description;
	// holds the description text
	JLabel descLabel;
	// image that appears inside the panel
	Image image = null;
	// holds the Image
	ImageIcon icon;
	// contains the ImageIcon
	JLabel imgLabel;
	//holds the size of the owner DesktopAlert
	//the size of the image dynamically depends on this size
	Dimension size;
	
	/**
     * Constructor of AlertPanel without image
     * 
     * @param desc		Description that will appear inside the alert
     * @param color1	Upper gradient color
     * @param color2	Lower gradient color
     * @param size		Size of the owner
     */
	public AlertPanel(String desc, Color color1, Color color2, Dimension size)
	{
		this.color1 = color1;
		this.color2 = color2;
		//centerize the text & enable wrapping using 'p'
		this.description =  "<html><p align=\"center\">" + desc + "</p></html>";
		this.size = size;
		this.init();
	}
	
	/**
     * Constructor of AlertPanel without image
     * 
     * @param image		Image that appears inside the alert
     * @param desc		Description that will appear inside the alert
     * @param color1	Upper gradient color
     * @param color2	Lower gradient color
     * @param size		Size of the owner
     */
	public AlertPanel(Image image, String desc, Color color1, Color color2, Dimension size)
	{
		this.image = image;
		this.color1 = color1;
		this.color2 = color2;
		//this.description = desc;
		this.description =  "<html><p align=\"center\">" + desc + "</p></html>";
		this.size = size;
		this.init();
		this.addImage();
	}
	
	/**
	 * Sets the layout, initializes all the components except image
	 * then adds the component
	 */
	private void init()
	{
		// BorderLayout is used for simplicity
		this.setLayout(new BorderLayout(10,10));
		// the Label containing the description is initialized
		descLabel = new JLabel(description,SwingConstants.CENTER);
		//setting the font may create trouble for localization
		// TO DO: there must be a setFont method in this class
		// so that font can be configured from the outside of the class
		descLabel.setFont(new Font("Sans Serif",Font.PLAIN,11));
		// the Label containing the description is added
		this.add(descLabel,BorderLayout.CENTER);
	}
	
	/**
	 * Resizes the image,
	 * initializes the Label containing image
	 * then adds the Label
	 */
	private void addImage()
	{
		// check if the image variable of this class is null
		if(this.image!=null)
		{	
			// dynamically set the size of the image
			// image_width is one third of the alert's width
			int width = (int) size.getWidth()/3;
			// image_height is half of the alert's height
			int height = (int) size.getHeight()/2;
			// resize the image
			this.image = image.getScaledInstance(width,height,1);
			// initialize the icon that holds the image
			this.icon = new ImageIcon(this.image);
			// initialize the Label with the icon
			this.imgLabel = new JLabel(icon);
			this.imgLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
			// add the Label
			this.add(imgLabel,BorderLayout.WEST);
		}
	}
	
	/**
	 * Removes the image from the panel
	 */
	private void removeImage()
	{
		// remove the Label that contains the image
		this.remove(imgLabel);
		// make the image null
		this.image = null;
	}

	/**
	 * Fills the panel with gradient paint
	 *
	 * @param	g	The Graphics
	 */
	public void paintComponent(Graphics g) 
	{
   		// g2d is a Graphics2D instance
   		Graphics2D g2d = (Graphics2D)g;
   		// get the bounds of the AlertPanel
   		Rectangle bounds = getBounds();
		 
      	// create a gradient Paint
       	Paint gradientPaint = new GradientPaint(0, bounds.y, color1,
		    						     		0, bounds.y + bounds.height, color2);
   		// set the gradientPaint as the Paint of the g2d
   		g2d.setPaint(gradientPaint);
   		// now draw a filled rectangle
   		g2d.fillRect(0, 0, bounds.width, bounds.height);
   		//paintChildren paints all other components of the panel
   		this.paintChildren(g);
   	}
   	
   	/**
	 * Sets the text that appears inside the panel
	 *
	 * @param	text	The text that apppears in the panel
	 */
   	public void setDescText(String text)
   	{
   		this.description =  "<html><p align=\"center\">" + text + "</p></html>";
   		this.descLabel.setText(description);
   	}
   	
   	/**
	 * Gets the text that appears inside the panel
	 *
	 * @return The text that appears in the panel
	 */
   	public String getDescText()
	{
		return this.descLabel.getText();
	}
	
	/**
	 * Sets the image that appears inside the panel
	 *
	 * @param	image	The image that apppears in the panel
	 *  				this image is dynamicaly resized when used
	 */
	public void setAlertImage(Image image)
	{
		this.image = image;
		addImage();
	}
	
	/**
	 * Removes the Image from the panel
	 */
	public void removeAlertImage()
	{
		removeImage();
	}
}