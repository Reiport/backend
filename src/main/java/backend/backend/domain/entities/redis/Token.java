package backend.backend.domain.entities.redis;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;

@RedisHash("tokens")
public class Token {

    @Id()
    @Indexed()
    private Integer userId;

    @Indexed()
    private String value;

    @TimeToLive()
    private Long expire;

    public Token() {
    }

    public Token(Integer userId, String value, Long expire) {
        this.userId = userId;
        this.value = value;
        this.expire = expire;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Long getExpire() {
        return expire;
    }

    public void setExpire(Long expire) {
        this.expire = expire;
    }

}
