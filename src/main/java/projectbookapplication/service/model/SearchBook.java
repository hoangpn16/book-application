package projectbookapplication.service.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchBook {
    private String title;
    private Integer iduser;
    private String type;
    private String author;
    private String yearRelease;
}
