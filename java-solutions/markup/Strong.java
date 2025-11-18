package markup;

import java.util.List;

class Strong extends Inline {
    public Strong(List<ListItemContent> element) {
        super(element);
    }

    @Override
    protected String openMark() {
        return "__";
    }

    @Override
    protected String openTex() {
        return "\\textbf{";
    }

    @Override
    protected String closeTex() {
        return "}";
    }
}
