package projectbookapplication.service.model;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FixBook {
    private String title;
    private String link_img;
    private String type;
    private String author;
    private String yearRelease;
    private String prices;
    private String description;
    private boolean favorite;
}
