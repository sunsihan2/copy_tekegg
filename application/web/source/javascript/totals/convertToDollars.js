export default function convertToDollars(amount) {
    let isInvalidValue = (amount === null) || (typeof (amount) === 'string');
    return isInvalidValue ? 0 : amount / 100;
}
