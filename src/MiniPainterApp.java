import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
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
		
		
		//Instantiate a scene object 
	    Scene scene = new Scene(layout, 700, 500);  
	      
	    //Append scene to the stage 
	    primaryStage.setScene(scene);
	    
	    //Set title to the Stage
	    primaryStage.setTitle("Mini Painter"); 
	           
	    //Displaying the contents of the stage 
	    primaryStage.show();
	    
	}
	
	
	
	// Program starting point
	public static void main(String[] args) {
		
		// Launch the application (internally calls the start() method of the Application class)
		launch(args);
	}

}
