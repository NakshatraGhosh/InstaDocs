package com.healthify.Healthyfy;





import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class Answer {
    private List<Part> contents;

    public List<Part> getContents() {
        return contents;
    }

    public void setContents(List<Part> contents) {
        this.contents = contents;
    }

    public static class Part {
        private List<TextPart> parts;

        public List<TextPart> getParts() {
            return parts;
        }

        public void setParts(List<TextPart> parts) {
            this.parts = parts;
        }
    }

    public static class TextPart {
        private String text;

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }
}
