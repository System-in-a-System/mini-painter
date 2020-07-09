import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;


public class MiniPainterApp extends Application {

	
	// Declare global variables for common use:
	
	// The number id of the chosen shape
	private int shapeID = 1;
	    
	// A set of potential shapes
	private Line line = new Line();
	private Rectangle rectangle = new Rectangle();
	private Circle circle = new Circle();
		
	    
	// Starting coordinate location
	private double prevX, prevY;   
		
	// Final coordinate location
	private double finalX, finalY;

	    
	// Dragging on/off switcher
	private boolean dragging;  

		
	// Global canvas where the drawing takes place
	private Canvas canvas;  
	    
	// Global ColorPicker
	ColorPicker colorPicker = new ColorPicker();

	// GraphicsContext object to perfrom drawing operations 
	private GraphicsContext g; 
	
	
	
	
	// Implement abstract method start() of the Application class 
	// Method that serves as the entry point of the application
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		
		// Instantiate a BorderPane layout
		BorderPane layout = new BorderPane();
		layout.setPadding(new Insets(5, 5, 5, 5));
		
		
		
		// ------------------------Setting up a horizontal box that holds combo boxes --------------------------//
		
		// Instantiate horizontal box for combo boxes
		HBox horizontalBox = new HBox();
					
		
		// ComboBox for shape choice
		Label shapesLabel = new Label(" Shape: ");
		ComboBox<String> shapes = new ComboBox<>();
		shapes.getItems().addAll("Dot", "Line", "Rectangle", "Circle");
				
		// ComboBox for size choice
		Label sizesLabel = new Label(" Size:  ");
		ComboBox<String> sizes = new ComboBox<>();
		sizes.getItems().addAll("1", "2", "3", "4", "5", "6", "7", "8", "9");
					
		// Adjust color Picker for colors choice			
		Label colorsLabel = new Label(" Color:  ");
		colorPicker.setValue(Color.BLACK);
								
		// Add clear button
		Button clearButton = new Button("Clear the mess");
					
					
					
					
		// Populate horizontal box 
		horizontalBox.getChildren().addAll(shapesLabel, shapes, 
		    								sizesLabel, sizes, 
											colorsLabel, colorPicker,
											clearButton);
					
		// Set inner margins within the horizontal box
		HBox.setMargin(shapesLabel, new Insets(3,3,3,3));
		HBox.setMargin(shapes, new Insets(3,3,3,3));
		HBox.setMargin(sizesLabel, new Insets(3,3,3,3));
		HBox.setMargin(sizes, new Insets(3,3,3,3));
		HBox.setMargin(colorsLabel, new Insets(3,3,3,3));
		HBox.setMargin(colorPicker, new Insets(3,3,3,3));
		HBox.setMargin(clearButton, new Insets(3,3,3,3));
					
					
		
		// Add horizontal box to the layout
		layout.setTop(horizontalBox);
		
		
		
		
		//---------------------------------- Setting up a canvas ------------------------------------------//
		
		// Instantiate Canvas  
        canvas = new Canvas(600,400);
        g = canvas.getGraphicsContext2D();
        
        // Set style
        int width = (int)canvas.getWidth();    
        int height = (int)canvas.getHeight();  

        g.setFill(Color.WHITE);
        g.fillRect(0,0,width,height);
        	        
        
        // Add canvas to the layout
        layout.setCenter(canvas);
        
        
        
        
        // ************************** Respond to the selection events on the combo boxes ******************//
        
        
        // Combo box for shapes
        shapes.valueProperty().addListener((obs, oldItem, newItem) -> {
        	if (shapes.getValue().equals("Dot"))
        		shapeID = 1;
        	else if (shapes.getValue().equals("Line"))
        		shapeID = 2;
        	else if (shapes.getValue().equals("Rectangle"))
        		shapeID = 3;
        	else if (shapes.getValue().equals("Circle"))
        		shapeID = 4;
        });
        
        
        // Combo box for sizes
        sizes.valueProperty().addListener((obs, oldItem, newItem) -> {
        	if (sizes.getValue().equals("1"))
        		g.setLineWidth(1);
        	else if (sizes.getValue().equals("2"))
        		g.setLineWidth(5);
        	else if (sizes.getValue().equals("3"))
        		g.setLineWidth(10);
        	else if (sizes.getValue().equals("4"))
        		g.setLineWidth(15);
        	else if (sizes.getValue().equals("5"))
        		g.setLineWidth(20);
        	else if (sizes.getValue().equals("6"))
        		g.setLineWidth(25);
        	else if (sizes.getValue().equals("7"))
        		g.setLineWidth(30);
        	else if (sizes.getValue().equals("8"))
        		g.setLineWidth(35);
        	else if (sizes.getValue().equals("9"))
        		g.setLineWidth(40);
        	
        	
        });
        
        
        
        // Respond to the mouse events on the canvas (drawing process)
        canvas.setOnMousePressed( e -> mousePressed(e) );
        canvas.setOnMouseDragged( e -> mouseDragged(e) );
        canvas.setOnMouseReleased( e -> mouseReleased(e) );
        
        
        
		
		//Instantiate a scene object 
	    Scene scene = new Scene(layout, 700, 500);  
	      
	    //Append scene to the stage 
	    primaryStage.setScene(scene);
	    
	    //Set title to the Stage
	    primaryStage.setTitle("Mini Painter"); 
	           
	    //Displaying the contents of the stage 
	    primaryStage.show();
	    
	}
	
	
	
	
	//---------------------------------Assisting methods for drawing--------------------------------//
	
	
	// Assisting method to be called when the user presses down the mouse within the canvas area
    public void mousePressed(MouseEvent evt) {

    	g.setStroke(colorPicker.getValue());
    	
    	// Ignore mouse presses if the drawing is already active
    	if (dragging == true)  
            return;            
    	
    	// Locate the coordinates where the starting click took place
        int x = (int)evt.getX();   
        int y = (int)evt.getY();   
        
        
        // Make sure the starting click is within the canvas
        int width = (int)canvas.getWidth();   
        int height = (int)canvas.getHeight(); 

        
        // If the click was within the canvas...
        if (x > 3 && x < width - 56 && y > 3 && y < height - 3) {
            // ... start drawing
        	
        	// Save the startig coordinates to global varialbes
            prevX = x;
            prevY = y;
            
            
            // Adjust drawing to the chosen shapes:
            
            if(shapeID == 2) {
            	line.setStartX(prevX);
                line.setStartY(prevY);
            }
            
            if(shapeID == 3) {
            	rectangle.setX(prevX);                
                rectangle.setY(prevY);
            }
            
            if(shapeID == 4) {
            	circle.setCenterX(prevX);
                circle.setCenterY(prevY);
            }
  
            
            // "Switch" dragging to true
            dragging = true;
        }

    } 

    
    

    // Assisting method to be called when the user releases the mouse button
    public void mouseReleased(MouseEvent evt) {
    	
    	// Locate the point of release
    	int x = (int)evt.getX();   
        int y = (int)evt.getY();   

        // Makes sure the point of release is within the canvas
        int width = (int)canvas.getWidth();    
        int height = (int)canvas.getHeight();  

        
        // If the point of release is within the canvas
        if (x > 3 && x < width - 56 && y > 3 && y < height - 3) {
            //... finalize the drawing object
        	
        	// Locate the final coordinates
            finalX = x;
            finalY = y;
            
            
            // Adjust drawing to the chosen shapes
            if(shapeID == 2) 
            	g.strokeLine(line.getStartX(), line.getStartY(), line.getEndX(), line.getEndY());
            if(shapeID == 3) {
            	rectangle.setWidth(Math.abs((finalX - rectangle.getX())));
             	rectangle.setHeight(Math.abs((finalY - rectangle.getY())));
             	g.fillRect(rectangle.getX(), rectangle.getY(), rectangle.getWidth(), rectangle.getHeight());
                g.strokeRect(rectangle.getX(), rectangle.getY(), rectangle.getWidth(), rectangle.getHeight());
            }
            if(shapeID == 4) {
            	circle.setRadius((Math.abs(finalX) - circle.getCenterX()) + Math.abs(finalY) - circle.getCenterY() / 2);
            	g.fillOval(circle.getCenterX(), circle.getCenterY(), circle.getRadius(), circle.getRadius());
                g.strokeOval(circle.getCenterX(), circle.getCenterY(), circle.getRadius(), circle.getRadius());

            }
            	
        }
    	
        // "Switch" dragging back to falses
    	dragging = false;
    }


    
    
    // Assisting method to be called when the user is dragging the mouse while mouse button is pressed down
    public void mouseDragged(MouseEvent evt) {

    	// If the mouse is not moving, return
        if (dragging == false)
            return;  

        
        // Keep track on the mouse position coordinates
        double x = evt.getX();   
        double y = evt.getY();   
        
        
        // Make sure coordinates are not reaching out of the canvas area 
        if (x < 3)                          
            x = 3;                           
        if (x > canvas.getWidth() - 57)       
            x = (int)canvas.getWidth() - 57;

        if (y < 3)                          
            y = 3;                           
        if (y > canvas.getHeight() - 4)       
            y = canvas.getHeight() - 4;

        
        
        // Dynamic drawing for dot shape and line shape
        if(shapeID == 1) 
        	g.strokeLine(prevX, prevY, x, y);    
        else if (shapeID == 2) {      	
        	line.setEndX(x);
            line.setEndY(y);      
        }
        	
        
        // Coordinates update        
        prevX = x;  
        prevY = y;

    } 
	
	
	
    
    
	// Program starting point
	public static void main(String[] args) {
		
		// Launch the application (internally calls the start() method of the Application class)
		launch(args);
	}

}
