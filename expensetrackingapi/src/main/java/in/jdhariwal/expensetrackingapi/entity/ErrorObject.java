package in.jdhariwal.expensetrackingapi.entity;

import java.sql.Date;

import lombok.*;

@Data
public class ErrorObject {

    private Integer statusCode;

    private String message;

    public Date timestamp;
    
}
