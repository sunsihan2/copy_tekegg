import expect from "expect";
import convertToDollars from "../javascript/totals/convertToDollars.js";

const scenarios = [[49], [null], ["poop"], [50], [99999999], [1000000]];
test.each(scenarios)('convertToDollarsTest amount:%p', (scenario) => {
    let expected = ((scenario === null) || (typeof (scenario) === 'string')) ? 0 : scenario / 100;
    let actual = convertToDollars(scenario);
    expect(actual).toEqual(expected);
})
