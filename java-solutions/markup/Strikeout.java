package markup;

import java.util.List;

public class Strikeout extends Inline {

    public Strikeout(List<ListItemContent> elements) {
        super(elements);
    }

    @Override
    protected String openMark(){
        return "~";
    }
    @Override
    protected String openTex(){
        return "\\textst{";
    }
    @Override
    protected String closeTex(){
        return "}";
    }
}
