package stream.file;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class TestWrite {
    public static void main(String[] args) {

        String currentDir = System.getProperty("user.dir");
        System.out.println("현재 작업 디렉터리: " + currentDir);

//        Thread t1 = new Thread(new NormalWriter(1));
//        Thread t2 = new Thread(new NormalWriter(2));
//        t1.start();
//        t2.start();

        Thread b1 = new Thread(new BufferedWriter(1));
        Thread b2 = new Thread(new BufferedWriter(2));
        b1.start();
        b2.start();

    }
}

class NormalWriter implements Runnable {
    private int processId;

    public NormalWriter(int id) {
        this.processId = id;
    }

    @Override
    public void run() {
        String currentDir = System.getProperty("user.dir");

        try (FileOutputStream fos = new FileOutputStream(currentDir  + "/output.txt")) {
            // 파일이 열릴 때마다 fd의 file offset은 0으로 시작(append=false 가정).
            for (int i = 1; i <= 100; i++) {
                String line = String.format("process %d, %dth line\n", processId, i);
                fos.write(line.getBytes());
                // fos.flush(); // flush()를 호출한다고 해도, 결과 interleave는 바뀔 가능성 여전히 존재
            }
        } catch (IOException e) {
            e.printStackTrace();
         }
    }
}


class BufferedWriter implements Runnable {
    private int processId;

    public BufferedWriter(int id) {
        this.processId = id;
    }

    @Override
    public void run() {
        String currentDir = System.getProperty("user.dir");

        try {
            BufferedOutputStream bos = new BufferedOutputStream(
                    new FileOutputStream(currentDir  + "/output.txt"));
            // 파일이 열릴 때마다 fd의 file offset은 0으로 시작(append=false 가정).
            for (int i = 1; i <= 100; i++) {
                String line = String.format("process %d, %dth line\n", processId, i);
                bos.write(line.getBytes());
                // fos.flush(); // flush()를 호출한다고 해도, 결과 interleave는 바뀔 가능성 여전히 존재
            }
            bos.flush();
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}