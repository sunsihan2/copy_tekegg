import postPayment from "../src/pages/payment/postPayment.js";
import { expect } from "expect";

const scenarios = [
  [
    {
      amount: 50,
      currency: "usd",
      source: "tok_visa",
      expected: true,
    },
  ],
  [
    {
      amount: 49,
      currency: "usd",
      source: "tok_visa",
      expected: false,
    },
  ],
  [
    {
      amount: 100,
      currency: "bogus",
      source: "tok_visa",
      expected: false,
    },
  ],
  [
    {
      amount: 9999999,
      currency: "usd",
      source: "bogus",
      expected: false,
    },
  ],
];
test.each(scenarios)("postPaymentTest", async (scenario) => {
  const expected = scenario.expected;

  const data = await postPayment(
    scenario.amount,
    scenario.currency,
    scenario.source
  );
  const actual = data.error == undefined ? true : false;
  expect(actual).toBe(expected);
});
