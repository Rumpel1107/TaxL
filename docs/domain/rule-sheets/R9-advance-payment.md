# R9 — Withholdings, advance payment and the final balance

**Source:** art. 807 E.T. · art. 383 E.T. · Form 210

## Values

| Filing history | Advance percentage |
|---|---|
| First return | 25% |
| Second return | 50% |
| Third return onwards | 75% |

## Formula

    base_current = net_income_tax(tax_year)
    base_average = (net_income_tax(tax_year) + net_income_tax(tax_year − 1)) / 2

    advance      = max(0, chosen_base × advance_percentage − withholdings(tax_year))

    balance      = net_income_tax + advance − withholdings − advance_paid_previous_year

## Conditions

- The advance belongs to the following tax year and is settled with the current return.
- From the third return onwards the filer chooses the base: the current year's net income tax, or
  the average of the last two. Both are lawful, so the engine takes the choice as an input and
  shows the result of each.
- The first two returns have no average to choose from; the current year's tax is the only base.
- The advance is never negative. Withholdings above the computed percentage yield zero, not a
  credit.
- Withholdings are an input taken from the certificates, never recomputed. Art. 383 governs how
  the withholding agent computed them; the return carries only the resulting figure.
- The advance settled with the previous return is subtracted in the current one.
- A positive balance is due; a negative one is a refund.
- **Open:** in the reference case neither base reproduces the advance actually settled. Now that
  the choice is established as the filer's, the open half is which base was used, not which one
  the law imposes.

## Test case

**Given** a third return with net income tax of $20.000.000, prior-year net income tax of
$10.000.000 and withholdings of $8.000.000
**When** the current-year base is chosen → 75% of $20.000.000, less withholdings, `advance` =
$7.000.000

**Given** the same case
**When** the average base is chosen → the average is $15.000.000, 75% of it is $11.250.000, less
withholdings, `advance` = $3.250.000, and the choice is the filer's

**Given** a first return with net income tax of $6.000.000 and withholdings of $5.000.000
**When** the advance resolves → 25% is $1.500.000, below the withholdings, so `advance` = $0

**Given** net income tax of $20.000.000, an advance of $7.000.000, withholdings of $8.000.000 and
$3.000.000 settled as advance with the previous return
**When** the balance resolves → $16.000.000 due
