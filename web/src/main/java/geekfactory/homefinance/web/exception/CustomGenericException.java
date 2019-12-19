package geekfactory.homefinance.web.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode
@ToString
@AllArgsConstructor
public class CustomGenericException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private String errCode;
    private String errMsg;
}
