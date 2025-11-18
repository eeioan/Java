package markup;

import java.util.List;

public class Emphasis extends Inline {

    public Emphasis(List<ListItemContent> elements) {
        super(elements);
    }

    @Override
    protected String openMark() {
        return "*";
    }


    @Override
    public void toTex(StringBuilder sb) {
        sb.append("\\emph{");
        elemToTex(sb);
        sb.append("}");
    }
}

