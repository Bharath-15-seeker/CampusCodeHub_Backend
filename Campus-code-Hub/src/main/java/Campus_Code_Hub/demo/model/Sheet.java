package Campus_Code_Hub.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "sheets")
public class Sheet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private SheetType sheetType;   // CODING / APTITUDE

    private String title;          // "DSA Coding Sheet", "Aptitude Sheet"

    @OneToMany(mappedBy = "sheet", cascade = CascadeType.ALL)
    private List<Topic> topics;
}

