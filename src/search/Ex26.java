package search;

/**
 * Day-26 사람의 수
 */
public class Ex26 {
    public static void main(String[] args) {
        int num = 3;        // 설문의 평균 갯수
        double[] inputs = {0.500, 0.250, 0.125};

        int numberOfPerson = 0;

        for (int i = 0; i < inputs.length; i++) {
            double avg = inputs[i];
            for (int j = 1; j <= 1000; j++) {
                if(isPass(j, avg)){
                    //System.out.println(j);
                    if(numberOfPerson < j){
                        numberOfPerson = j;
                    }
                    break;
                }
            }
        }
        System.out.println(numberOfPerson);
    }

    private static boolean isPass(int num, double avg) {
        boolean check = false;         // 통과 가능을 우선 false로 설정
        int min = 0;                   //최소 값
        int max = num * 10;            // 사람 수에 10곱한 값 - 최대 값

        for (int i = min; i <= max; i++) {
            if(i/(double)num == avg){
                check = true;
                break;
            }
        }
        return check;
    }
}
