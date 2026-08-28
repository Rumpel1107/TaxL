# Backlog P0 — Excel calculation engine (closed)

> **What goes in this document:** the user stories that specified the Excel engine. They are
> closed; this file exists because they are the input material for the calculation-engine spec
> in code, not because there is work left in them.
>
> Moved verbatim out of `PLAN.md` §8 on 2026-08-24. Two stories were NOT closed and live in
> `docs/roadmap.md` instead: **US-P0-000** (rule sheets) and **US-P0-009** (WTP probe).
> Status of each rule is tracked in `docs/domain/rules.md`, not here.

---

## Stories

**US-P0-001 · Parameters Sheet** `[R+A]`
*As* the engine builder, *I want* a sheet with the UVT and all thresholds/limits by tax year, *so that* the engine doesn't depend on hardcoded values and works every year.
- AC1: A `Parameters` sheet exists with a "Tax year" column.
- AC2: Includes UVT 2025 = 49,799 COP and thresholds: income/purchases/deposits (1,400 UVT), net worth (4,500 UVT). *(pending rule sheet — see US-P0-000)*
- AC3: Includes limits: general 40% cap; 25% exempt income (cap 790 UVT); dependents (384 UVT or 10%); mortgage interest (100 UVT); prepaid health (192 UVT); GMF (50%); additional dependents (72 UVT each, max 4). *(pending rule sheet)*
- AC4: All other sheets reference this one via formula (zero hardcoded literals).

**US-P0-002 · Input Capture** `[A]`
*As* a user, *I want* an input sheet with clearly marked cells, *so that* I know exactly what to enter.
- AC1: Input cells visually distinguishable (e.g., blue fill).
- AC2: Grouped by: income, mandatory contributions (INCRNGO), deductions, exempt income, net worth.
- AC3: Each cell has a clear label and unit.

**US-P0-003 · Filing Obligation Determination (4 thresholds)** `[R+A]`
*As* a user, *I want* to know whether I'm required to file based on the 4 thresholds, *so that* I can decide whether to continue.
- AC1: Evaluates income, purchases, bank deposits, and net worth against their thresholds.
- AC2: Indicates which threshold(s) were exceeded.
- AC3: Clear result: "Required / Not required" + reason.

**US-P0-004 · General Schedule Calculation (Labor Income)** `[R+A]`
*As* an employee, *I want* my taxable income calculated by applying non-taxable income, deductions, and exempt income under the 40% cap, *so that* I get the taxable base.
- AC1–AC2: applies INCRNGO, deductions and exemptions in the legally correct order.
- AC3: Applies the general 40% cap on deductions + exempt income combined.
- AC4: Separates **capped** deductions from **uncapped** ones (additional dependents 72 UVT; 1% electronic-invoiced purchases).
- AC5: Reproduces the result of Example #1 from the reference webinar.

**US-P0-005 · Tax Liability Calculation** `[R+A]`
*As* a user, *I want* the tax calculated using the Art. 241 progressive table, *so that* I know the amount owed.
- AC1: Converts the taxable base to UVT using that year's UVT value.
- AC2: Locates the correct bracket and applies the table's formula.
- AC3: For Example #1 (employee), yields **$0** tax due.

**US-P0-006 · Wealth/Equity Comparison** `[R+A]`
*As* a user, *I want* to compare last year's net worth vs. this year's, *so that* I can spot unjustified increases.
- AC1: Calculates the change in net worth.
- AC2: Flags whether the increase is unsupported by income/appreciation/occasional gains.

**US-P0-007 · Output Mapped to Form 210** `[R+A]`
*As* a user, *I want* to see which value goes in each line of Form 210, *so that* I can transfer it to the DIAN.
- AC1: Line → value table.
- AC2: Covers net worth (29–31), labor income (32–42 / 43–57), occasional gains, tax due, advance payment, balance.
- AC3: Every line traceable back to an Input or a Parameter.

**US-P0-008 · Validation Test Suite** `[R]`
*As* the product owner, *I want* a set of test cases with expected results, *so that* I can confirm the engine is reliable.
- AC1: Includes the **3** real reference returns, whose values are held in the local fixture.
- AC2: Includes the webinar's worked examples (employee → $0; refund 1,886,000 COP; self-employed ×3; capital income; non-labor; pensions; occasional gains).
- AC3: Pass criterion: $0 difference on key lines.
