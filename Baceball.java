package gittest;

import java.util.Scanner;

// 야구게임
public class Baceball {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // 정답 설정
        int[] answer = trueAnswer(); // 정답 배열을 생성하는 메서드 호출

        System.out.println("컴퓨터가 숫자를 생성하였습니다. 답을 맞춰보세요!");

        int attempts = 0; // 시도 횟수 초기화

        while (true) { // 무한 루프 - 사용자가 정답을 맞출 때 까지 반복
            attempts++; // 시도 횟수 증가
            System.out.printf("%d 번째 시도 : ",attempts); // 시도 횟수 출력
            String input = scanner.next(); // 사용자로 부터 세 자리 숫자를 입력받음

            if (input.length() != 3 || !input.matches("[0-9]+")) {
                System.out.println("세 자리 숫자를 입력해 주세요."); // 잘못 입력이 될 경우 메시지 출력
                continue; // 다시 입력 받기 위해 반복문을 계속함
            }

            int[] guess = new int[3];
            for (int i = 0; i < 3; i++) {
                guess[i] = Character.getNumericValue(input.charAt(i)); // 문자열로부터 숫자를 추출하여 배열에 저장
            }
            // 스트라이크(S)와 볼(B) 계산
            int strikes = 0;
            int balls = 0;

            for (int i = 0; i < 3; i++) {
                if (guess[i] == answer[i]) { // 위치와 값이 모두 일치하는 경우 스트라이크
                    strikes++;
                } else {
                    for (int j = 0; j < 3; j++) {
                        if (guess[i] == answer[j]) { // 값은 일치하지만 위치가 다른 경우 볼
                            balls++;
                        }
                    }
                }
            }
            // 결과 확인 및 출력
            if (strikes == 3) { // 세 자리 숫자를 모두 맞춘 경우
                System.out.printf("%d 번째 만에 맞췄습니다.\n", attempts);
                break; // 게임 종료
            } else { // 세 자리 숫자를 맞추지 못한 경우
                System.out.printf("%dS %dB\n", strikes, balls); // 스트라이크와 볼의 갯수 출력
            }
        }
        // 숫자 3개를 맞춘 경우
        System.out.println("게임을 종료합니다."); // 게임을 종료한다는 메시지 출력
        scanner.close(); // 사용자 입력창 닫기
    }

    // 정답 생성 메서드
    private static int[] trueAnswer() {
        int[] answer = new int[3]; // 세 자리의 정답 배열 생성
        boolean[] used = new boolean[10]; // 사용된 숫자를 체크하기 위한 배열
        int index = 0;
        while (index < 3) { // 세 자리의 숫자를 모두 설정할 때까지 반복 [0,1,2]
            int num = (int) (Math.random() * 10); // 0에서 9까지 랜덤한 숫자를 생성
            if (!used[num]) { // 해당숫자가 사용되지 않은 경우
                answer[index++] = num; // 정답 배열에 숫자 추가
                used[num] = true; // 사용된 숫자로 체크
            }
        }
        return answer; // 완성된 배열을 리턴
    }
}

