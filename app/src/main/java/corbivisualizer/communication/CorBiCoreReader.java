package corbivisualizer.communication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class CorBiCoreReader
{
    public interface HeartRateListener
    {
        void onHeartRateChanged(double heartRate);
    }

    private HeartRateListener hRateListener;

    public CorBiCoreReader(HeartRateListener hRateListener)
    {
        this.hRateListener = hRateListener;
    }

    public void readDummy()
    {
        // HACK いいからプロトタイピングだ！！
        // TODO 雑な書き方なので、Javaがプロセスを離すとブロックorデットロックになるかも。

        ProcessBuilder pb = new ProcessBuilder("../../dummyProcess/main");//　 FIXME　なんかこの辺、win macで違うっぽい
        pb.redirectErrorStream(true);

        try
        {
            Process process = pb.start();
            //System.out.println(process.pid()); //掴んだプロセスのID取得

            BufferedReader bReader = new BufferedReader(
                    new InputStreamReader(process.getInputStream(), Charset.defaultCharset()));
            String line;
            while ((line = bReader.readLine()) != null)
            {
                hRateListener.onHeartRateChanged(Double.parseDouble(line));
                System.out.println("catched: " + line);
            }
            process.destroy();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    public void readCorBiCore()
    {

    }

}
