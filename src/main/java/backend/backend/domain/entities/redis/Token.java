package backend.backend.domain.entities.redis;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@RedisHash("tokens")
@Getter()
@Setter()
public class Token {

    @Id()
    @Indexed()
    private Integer userId;

    @Indexed()
    private String value;

    @TimeToLive()
    private Long expire;

}