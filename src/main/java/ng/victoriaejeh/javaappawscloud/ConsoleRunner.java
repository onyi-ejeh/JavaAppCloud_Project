package ng.victoriaejeh.javaappawscloud;

import ng.victoriaejeh.javaappawscloud.models.HockeyPlayer;
import ng.victoriaejeh.javaappawscloud.models.HockeyPlayerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ConsoleRunner implements CommandLineRunner {

    @Autowired
    private HockeyPlayerRepo hockeyPlayerRepo;

    @Override
    public void run(String... args) throws Exception {
        if (hockeyPlayerRepo.count() == 0) {
            HockeyPlayer hockeyPlayer = new HockeyPlayer();
            hockeyPlayer.setAge(52);
            hockeyPlayer.setName("Onyi");
            hockeyPlayerRepo.save(hockeyPlayer);

            hockeyPlayer = new HockeyPlayer();
            hockeyPlayer.setAge(34);
            hockeyPlayer.setName("Vicky");
            hockeyPlayerRepo.save(hockeyPlayer);
        } else {
            System.out.println("Hockey players are already present in the database.");
        }
    }
}
