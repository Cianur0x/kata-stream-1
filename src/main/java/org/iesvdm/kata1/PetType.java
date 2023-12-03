
package org.iesvdm.kata1;

public enum PetType {
    CAT("🐱"),
    DOG("🐶"),
    HAMSTER("🐹"),
    TURTLE("🐢"),
    BIRD("🐦"),
    SNAKE("🐍");

    private final String emoji;

    PetType(String emoji) {
        this.emoji = emoji;
    }

    @Override
    public String toString() {
        return this.emoji;
    }

    public static PetType fromEmoji(String searchEmoji) {
        // Find the correct PetType based on the String emoji
        PetType petType = null;
        switch (searchEmoji) {
            case "🐱":
                petType = PetType.CAT;
                break;
            case "🐶":
                petType = PetType.DOG;
                break;

            case "🐹":
                petType = PetType.HAMSTER;
                break;
            case "🐢":
                petType = PetType.TURTLE;
                break;
            case "🐦":
                petType = PetType.BIRD;
                break;

            case "🐍":
                petType = PetType.SNAKE;
                break;
        }
        return petType;
    }
}
