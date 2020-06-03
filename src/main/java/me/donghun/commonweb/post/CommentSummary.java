package me.donghun.commonweb.post;

import org.springframework.beans.factory.annotation.Value;

public interface CommentSummary {

    String getComment();

    int getUp();

    int getDown();

    // closed projection
    // query 최적화도 되고 alias도 만들 수 있고
    default String getVotes(){
        return getUp() + " " + getDown();
    }

/*    // open projection. target이 Comment인데 up과 down을 가져오기 위해 결국 target을 다 가져와야 하므로
    // 즉, open projection은 성능 최적화랑은 관계가 멀다
    // 근데 sql alias를 만들 수 있다
    @Value("#{target.up + ' ' + target.down}")
    String getVotes();*/
}
