export const movementMessage = (numberOfMovement: number) => {
    if (numberOfMovement <= 0) {
        return 'No movement detected today';
    } else if (numberOfMovement === 1) {
        return 'movement detected today';
    } else {
        return 'movements detected today';
    }
};
