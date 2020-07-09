import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.BorderPane;
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
