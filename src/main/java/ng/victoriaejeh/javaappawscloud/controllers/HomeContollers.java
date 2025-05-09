package ng.victoriaejeh.javaappawscloud.controllers;
import ng.victoriaejeh.javaappawscloud.models.HockeyPlayerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeContollers extends BaseController {
    @Autowired
    private HockeyPlayerRepo hockeyPlayerRepo; // Autowire the BookRepository to access database operations
    // This is a placeholder for the HomeController class.
    // You can add methods and logic here to handle requests and responses.
    // For example, you might want to add a method to return the home page view.
    // en controller är en klass som mappar
    // HTTP-förfrågningar till specifika metoder i din applikation.
    //funktionen ska då retunera HTML för den sidan

    // Exempel:

    @GetMapping("/")
    public String home(Model model) {
        // Logik för att hantera hem-sidan
        // Du kan returnera en vy eller JSON-data här
        // Hämta saker från databasen eller annan källa
        // List<String> books = bookService.getAllBooks();
        System.out.println("Home page accessed");
        //List<String> books = new ArrayList<>();
        //books.add("Book 1");
        //books.add("Book 2");
        //books.add("Book 3");

        model.addAttribute("hockey",hockeyPlayerRepo.findAll()); // Lägger till en lista med böcker i modellen
        model.addAttribute("user", "Vicky"); // Lägger till en användare i modellen

        return "home"; // Returnerar namnet på HTML-sidan som ska visas
    }

    @GetMapping("/profile")
    public String profile(Model model) {
        // Logik för att hantera profil-sidan
        // Du kan returnera en vy eller JSON-data här
        System.out.println("Profile page accessed");
        String user = getLoggedInEmail(); // Hämtar den inloggade användarens e-postadress
        model.addAttribute("user", user); // Lägger till användaren i modellen

        return "profile"; // Returnerar namnet på HTML-sidan som ska visas
    }

}