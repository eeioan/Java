package markup;
import java.util.List;

public class Emphasis extends Inline{

        public Emphasis(List<ListItemContent> elements) {
            super(elements);
        }
        @Override
        public void toMarkdown(StringBuilder sb) {
            sb.append("*");
            elemToMarkdown(sb);
            sb.append("*");
        }

        @Override
        public void toTex(StringBuilder sb) {
            sb.append("\\emph{");
            elemToTex(sb);
            sb.append("}");
        }
    }

