package markup;

import java.util.List;

class Strong extends Inline {
    public Strong(List<ListItemContent> element) {
        super(element);
    }

    @Override
    public void toMarkdown(StringBuilder sb) {
        sb.append("__");
        elemToMarkdown(sb);
        sb.append("__");
    }

    @Override
    public void toTex(StringBuilder sb) {
        sb.append("\\textbf{");
        elemToTex(sb);
        sb.append("}");
    }
}
