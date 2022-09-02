import { expect } from "expect";
import parsePaymentResponse from "../src/pages/payment/parsePaymentResponse.js";
const scenarios = [
  [
    {
      status: "succeeded",
      paid: true,
    },
  ],
  [
    {
      status: "failed",
      paid: false,
    },
  ],
  [
    {
      status: "succeeded",
      paid: false,
    },
  ],
  [
    {
        status: "failed",
        paid: true,
      },
  ],
  [
    {},
  ]
];

test.each(scenarios)("parsePaymentTest", (scenario) => {
  const expected =  scenario.hasOwnProperty("status") && scenario.status == "succeeded" && scenario.paid;
  const actual = parsePaymentResponse(scenario);
  expect(actual).toBe(expected);
});
