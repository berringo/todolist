
package berringo.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@NamedQueries({
        @NamedQuery(name = "findUserByEmail", query = "SELECT u FROM Users u where u.email = :p_email"),
        @NamedQuery(name = "findUserByEmailAndPassword", query = "SELECT u FROM Users u where u.email = :p_email and u.password = :p_password"),
        @NamedQuery(name = "findAllUsers", query = "SELECT u FROM Users u "),
        @NamedQuery(name = "validUser", query= "Select u from Users u where u.name = ?1 and u.password = ?2")
})
public class Users implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    private String name;
    private String email;
    private String password;

    public Users() {
    }

    public Users(long id, String name, String email, String password) {
        this.id=id;
    	this.name = name;
        this.email = email;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User[" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ']';
    }
}
