import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;

// Annotation-based servlet mapping
@WebServlet("/cylinderVolume")
public class CylinderVolumeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        // Start HTML response with a linked CSS file for styling
        out.println("<html><head>");
        out.println("<link rel='stylesheet' type='text/css' href='" + request.getContextPath() + "/css/styles.css'>");
        out.println("</head><body>");
        
        // Display a form for user input
        out.println("<div class='container'>");
        out.println("<h2>Cylinder Volume Calculator</h2>");
        out.println("<form action='" + request.getContextPath() + "/cylinderVolume' method='POST'>");
        out.println("Radius: <input type='text' name='radius' required><br>");
        out.println("Height: <input type='text' name='height' required><br>");
        out.println("<button type='submit'>Calculate Volume</button>");
        out.println("</form>");
        out.println("</div>");
        
        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        // Start HTML response with CSS linking
        out.println("<html><head>");
        out.println("<link rel='stylesheet' type='text/css' href='" + request.getContextPath() + "/css/styles.css'>");
        out.println("</head><body>");
        
        out.println("<div class='container'>");

        try {
            // Parse radius and height from the request
            double radius = Double.parseDouble(request.getParameter("radius"));
            double height = Double.parseDouble(request.getParameter("height"));
            
            // Validate that both radius and height are positive
            if (radius < 0 || height < 0) {
                throw new IllegalArgumentException("Radius and Height must be positive values.");
            }
            
            // Calculate the volume using the cylinder formula: πr²h
            double volume = Math.PI * Math.pow(radius, 2) * height;
            
            // Display the result
            out.println("<h2>Cylinder Volume Result</h2>");
            out.println("<p>Radius: " + radius + "</p>");
            out.println("<p>Height: " + height + "</p>");
            out.println("<p>Volume: " + volume + " cubic units</p>");
            
        } catch (NumberFormatException e) {
            // Handle invalid numeric input
            out.println("<h2 class='error-heading'>Error: Invalid Input</h2>");
            out.println("<p class='error-message'>Please enter valid numeric values for radius and height.</p>");
        } catch (IllegalArgumentException e) {
            // Handle negative values
            out.println("<h2 class='error-heading'>Error: Invalid Input</h2>");
            out.println("<p class='error-message'>" + e.getMessage() + "</p>");
        }
       
        // Provide a link to go back to the input form
        out.println("<a href='" + request.getContextPath() + "/cylinderVolume' class='go-back-link'>Go back</a>");
        out.println("</div>");
        out.println("</body></html>");
    }
}
