public enum ScholarStatus {
    REGULAR,
    IRREGULAR,
    PROBATION;

    @Override
    public String toString() {
        switch (this) {
            case REGULAR:
                return "Regular";
            case IRREGULAR:
                return "Irregular";
            case PROBATION:
                return "Probation";
            default:
                return "";
        }
    }
}
