# R4 — 25% exempt labor income, cap 790 UVT

**Source:** art. 206 num. 10 and par. 5 E.T. (Ley 2277 de 2022 art. 2); art. 336 num. 4 E.T.;
DUT 1625 de 2016 art. 1.2.1.20.4

## Values

| Tax year | 790 UVT |
|---|---|
| 2023 | $33.505.480 |
| 2024 | $37.181.350 |
| 2025 | $39.341.210 |

## Formula

    base_labor = labor_payments − incrngo − deductions − afc_voluntary_pension − exempt_severance
    base_fees  = fee_payments − incrngo_fees − deductions_fees − other_exempt_fees
                 (zero when the taxpayer elects costs and expenses)
    exempt_25  = min(0.25 × base_labor + 0.25 × base_fees, 790 × uvt(tax_year))

## Conditions

- The base is labor payments only, not the whole general schedule.
- The base is net: INCRNGO (R3), the schedule's deductions (R5, R6) and its other exempt
  income — AFC / voluntary pension (R6) and exempt severance pay (R15) — are all subtracted
  before the 25% is applied.
- The 72 UVT per dependent (art. 336 num. 3) and the 1% electronic-invoice deduction are not
  subtracted from the base; they fall outside the general cap (R7).
- The 790 UVT cap is annual, and applies after the 25%.
- The exemption is never negative: a negative net base yields zero, not a negative exemption.
- Inside the general 40% / 1.340 UVT cap (R7).
- A published worked example computes the 25% on labor payments net of INCRNGO alone, without
  subtracting deductions or other exempt income. It is wrong: the net base is what reproduces
  the reference returns.
- There is one exemption, not two. Par. 5 extends num. 10 to work income that does not come from
  an employment or statutory relationship; it creates no parallel benefit for fees.
- Each sub-schedule is depurated on its own — fees carry their own INCRNGO, the mandatory health
  and pension contributions paid on that income — and the 25% is resolved on each net base.
- The 790 UVT cap applies to the **sum** of both results, never one cap per sub-schedule. A cap per
  sub-schedule would grant 1.580 UVT to a mixed case, twice the legal benefit.
- Fees only: the taxpayer must choose between subtracting costs and expenses or taking this
  exemption (art. 336 num. 4). They are mutually exclusive, and the choice belongs to the taxpayer,
  never to the engine.
- Ley 2277 de 2022 removed the earlier condition of hiring fewer than two workers for under 90
  days. Any taxpayer with fees income may opt today.

## Test case

**Given** labor payments of $100.000.000, INCRNGO of $8.000.000 and deductions of $12.000.000,
tax year 2025
**When** the exemption is resolved → base $80.000.000, 25% governs, `exempt_25` = $20.000.000

**Given** labor payments of $250.000.000, INCRNGO of $20.000.000 and deductions of $30.000.000,
tax year 2025
**When** the exemption is resolved → base $200.000.000, the 790 UVT cap governs, `exempt_25` =
$39.341.210

**Given** the first case plus $10.000.000 of AFC contributions
**When** the exemption is resolved → base $70.000.000 and `exempt_25` = $17.500.000, not
$20.000.000

**Given** a net labor base of $120.000.000 and a net fees base of $80.000.000, tax year 2025
**When** the exemption is resolved → the two 25% shares are $30.000.000 and $20.000.000, and the
790 UVT cap governs their sum: `exempt_25` = $39.341.210, not $50.000.000 and not $78.682.420

**Given** the same case with the taxpayer electing costs and expenses on the fees
**When** the exemption is resolved → `base_fees` is zero and `exempt_25` = $30.000.000, the labor
share alone
