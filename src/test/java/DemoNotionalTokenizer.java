import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.tokenizer.NotionalTokenizer;
import com.hankcs.hanlp.tokenizer.StandardTokenizer;

import java.util.List;

/**
 * Created by tiefanding on 2020/3/18.
 */
public class DemoNotionalTokenizer {
    public static void main(String[] args)
    {
        String text = "小区居民有的反对喂养流浪猫，而有的居民却赞成喂养这些小宝贝";
        // 自动去除停用词
        List<Term> aa  = NotionalTokenizer.segment(text);
        System.out.println(aa);    // 停用词典位于data/dictionary/stopwords.txt，可以自行修改
//        // 自动断句+去除停用词
        for (Term sentence : NotionalTokenizer.segment(text))
        {
            System.out.println(sentence.word);
        }
    }
}
