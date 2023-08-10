package vn.ping.ping_todo_api.error;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class AppException {
    private int code;
    private String message;
}
