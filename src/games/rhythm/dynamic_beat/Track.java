package games.rhythm.dynamic_beat;

import lombok.Getter;
import lombok.Setter;

/**
 * 하나의 음악에 대한 정보를 담는 클래스
 */
@Getter
@Setter
public class Track {

    private String titleImage;  // 트랙 제목 부분 이미지
    private String startImage;  // 게임 선택 창 표지 이미지
    private String gameImage;   // 해당 곡을 실행했을 때 표지 이미지
    private String startMusic;  // 게임 선택 창에서 해당 곡을 실행했을 때 재생되는 음악
    private String gameMusic;   // 해당 곡을 실행했을 때 재생되는 음악
    private String titleName;   // 곡 제목
    /**
     * Track 클래스의 생성자
     */
    public Track(String titleImage, String startImage, String gameImage, String startMusic, String gameMusic, String titleName) {
        this.titleImage = titleImage;
        this.startImage = startImage;
        this.gameImage = gameImage;
        this.startMusic = startMusic;
        this.gameMusic = gameMusic;
        this.titleName = titleName;
    }
}
