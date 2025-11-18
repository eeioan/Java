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
    public String openTex() {
        return "\\emph{";
    }

    @Override
    public String closeTex() {
        return "}";
    }

}

