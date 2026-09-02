# R17 — AFC contingent withholding on withdrawal

**Source:** art. 126-4 E.T. incisos 2–3 and parágrafos 1–2 · DUT 1625 de 2016 art. 1.2.4.1.32 ·
DIAN Concepto 4 de 2002 · DIAN Oficio 33234 de 2018

## Values

Two mechanisms coexist, and which one applies depends on where the contributed money came from.

| Contribution origin | Contingency the entity registers | Statute |
|---|---|---|
| Excluded from withholding when deposited — typically channelled through payroll | The withholding not practised then | inciso 2 |
| Not excluded, but claimed as exempt income | 7% of the contribution | parágrafo 2 |

## Formula

    compliant = permanence ≥ 10 years   (deposits from 1-jan-2013 — Ley 1819 de 2016)
             or destination = housing   (purchase, mortgage payment, housing leasing)

    withdrawal_taxable = if compliant then 0 else withdrawn_amount
    withholding        = if compliant then 0 else registered_contingency

## Conditions

- A compliant withdrawal keeps the exempt status and does not enter the return of the year it is
  withdrawn in. Nothing is declared.
- A non-compliant withdrawal is taxable income of the year of withdrawal. The withholding the
  entity practises does not settle it: the filer declares the withdrawal and credits the
  withholding like any other.
- Housing destination is not conditioned on the loan being held by a supervised entity.
- The account-holding entity keeps the control account, verifies the destination against
  supporting documents, practises the withholding when the withdrawal fails, and certifies the
  movements annually. What it certifies is an input to the return, never recomputed here.
- One certificate may split a balance between contingent and non-contingent amounts, according to
  the channel each deposit came through.
- Parágrafo 2 makes the withdrawal taxable only where the contribution produced a tax benefit. The
  share lost to the general cap (`lost_to_cap`, R7) produced none; whether that share is taxable
  on a non-compliant withdrawal is unsettled, and the burden of proof falls on the filer. It is a
  reason to keep `lost_to_cap` traceable per tax year.
- **Out of MVP scope:** the non-compliant withdrawal is not modelled. What the engine models is
  the exempt contribution under the shared 30% / 3.800 UVT cap (R6) and the compliant withdrawal,
  which has no effect on the return.

## Test case

**Given** an AFC withdrawal of $30.000.000 destined to the purchase of housing, with the
supporting documents the entity requires
**When** the rule resolves → `withdrawal_taxable` = $0, `withholding` = $0, and the withdrawal
does not appear in the return

**Given** a withdrawal of $30.000.000 after four years of permanence and no housing destination
**When** the rule resolves → the whole amount is taxable income of the year of withdrawal and the
entity practises the registered contingency. Out of MVP scope: the engine flags the case instead
of computing it
