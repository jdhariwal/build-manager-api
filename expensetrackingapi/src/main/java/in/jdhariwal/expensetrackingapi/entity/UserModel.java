package in.jdhariwal.expensetrackingapi.entity;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class UserModel {

    @NotBlank(message = "Field shouldn't be empty")
    private String name;

    @NotNull(message = "Field shouldn't be empty")
    @Email(message = "Enter a valid email")
    private String email;

    @NotNull(message = "Password shouldn't be empty")
    @Size(min = 5, message = "Password should be atleast 5 characters")
    private String password;
    private Long age = 0L;
}
