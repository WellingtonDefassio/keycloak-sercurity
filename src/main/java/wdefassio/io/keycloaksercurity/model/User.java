package wdefassio.io.keycloaksercurity.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
    private String name;
    private String lastName;
    private String id;

}
