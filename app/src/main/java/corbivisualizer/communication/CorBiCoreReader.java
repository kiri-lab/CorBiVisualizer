package corbivisualizer.communication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class CorBiCoreReader
{
    private ProcessBuilder pb;
    private Process process;

    public interface HeartRateListener
    {
        void onHeartRateChanged(double heartRate);
    }

    private HeartRateListener hRateListener;

    public CorBiCoreReader(HeartRateListener hRateListener)
    {
        this.hRateListener = hRateListener;
    }

    public CorBiCoreReader setDummy()
    {
        pb = new ProcessBuilder("../../CorBidummyProcess/Debug/dummyProcess.exe"); //win
        pb = new ProcessBuilder("../../dummyProcess/main"); // mac
        return this;
    }

    public CorBiCoreReader setCorBiCore()
    {
        pb = new ProcessBuilder("../../CorBiCore/main");//　 FIXME　なんかこの辺、win macで違うっぽい
        return this;
    }

    public void read()
    {
        pb.redirectErrorStream(true);
        try
        {
            this.process = pb.start();
            //System.out.println(process.pid()); //掴んだプロセスのID取得

            BufferedReader bReader = new BufferedReader(
                    new InputStreamReader(process.getInputStream(), Charset.defaultCharset()));
            String line;
            while ((line = bReader.readLine()) != null)
            {
                hRateListener.onHeartRateChanged(Double.parseDouble(line));
                System.out.println("catched: " + line);
            }
            this.destroy();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    public void destroy()
    {
        this.process.destroy();
    }
}
