package games.rhythm.dynamic_beat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Beat {
    private int time;
    private String noteName;

    public Beat(int time, String noteName) {
        this.time = time;
        this.noteName = noteName;
    }

}
