# R16 — Meal allowance payments

**Source:** art. 387-1 E.T.

## Values

Both figures are monthly, and the table gives the exact product (R1).

| Tax year | 310 UVT — salary ceiling | 41 UVT — allowance cap |
|---|---|---|
| 2023 | $13.147.720 | $1.738.892 |
| 2024 | $14.590.150 | $1.929.665 |
| 2025 | $15.437.690 | $2.041.759 |

## Formula

    salary_ceiling    = 310 × uvt(tax_year)
    allowance_cap     = 41 × uvt(tax_year)

    taxable_allowance = if monthly_salary > salary_ceiling
                          then allowance_paid
                          else max(0, allowance_paid − allowance_cap)

## Conditions

- The benefit covers the employer's payments to third parties for the worker's or the family's
  meals, including the purchase of food vouchers.
- Two conditions, both monthly, and both must hold: the worker's salary must not exceed 310 UVT,
  and only 41 UVT per month is excluded.
- If the salary condition fails, the whole payment is income. The 41 UVT exclusion does not
  apply at all — it is not a partial allowance for higher earners.
- What exceeds 41 UVT is labor income of the worker and is subject to withholding.
- **Open:** both tests are monthly in the statute, and the engine applies them annually —
  dividing salary by twelve and multiplying the cap by twelve. The two readings agree only when
  salary and allowance are even across the months.

## Test case

**Given** a monthly salary of $10.000.000 and a meal allowance of $1.500.000 per month, tax
year 2025
**When** the rule resolves → both conditions hold and `taxable_allowance` = $0

**Given** a monthly salary of $10.000.000 and a meal allowance of $3.000.000 per month
**When** the rule resolves → the cap governs and `taxable_allowance` = $958.241 per month

**Given** a monthly salary of $20.000.000 and a meal allowance of $1.500.000 per month
**When** the rule resolves → the salary exceeds 310 UVT, so the whole $1.500.000 is income and
no part of the 41 UVT exclusion applies

**Given** a monthly salary of $10.000.000, no allowance for eleven months and $24.000.000 in
the twelfth
**When** the rule resolves month by month → `taxable_allowance` = $21.958.241. Read annually
against a cap of twelve times 41 UVT it would be $0
