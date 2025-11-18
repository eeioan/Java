package markup;

import java.util.List;

public class Strikeout extends Inline {

    public Strikeout(List<ListItemContent> elements) {
        super(elements);
    }

    @Override
    public void toMarkdown(StringBuilder sb) {
        sb.append("~");
        elemToMarkdown(sb);
        sb.append("~");
    }

    @Override
    public void toTex(StringBuilder sb) {
        sb.append("\\textst{");
        elemToTex(sb);
        sb.append("}");
    }
}
