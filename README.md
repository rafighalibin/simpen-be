## Syntax

`<author>/<type>/<pbi-number>/<title>`

## Examples

```
sandiagados/feature/PBI-1/create-account
```

```
sandiagados/bug/PBI-1/fix-invalid-jwt-token
```

```
sandiagados/spike/PBI-6/incorporate-gform-to-report
```

## Explanation

#### `<author>`

GitHub username.

#### `<type>`

```
bug      - Code changes linked to a known issue
feature  - New feature
refactor - Code refactoring
spike    - Experiments
```

#### `<pbi-number>`

PBI number.

#### `<name>`

- Use `kebab-case`
- Keep it concise

## Get Logged User

- service di auth --> getLoggedUser(HttpServletRequest request)
- contoh: nembak API auth --> Get /login
- additional:
  - get id --> getIdLoggedUser(HttpServletRequest request)
  - get role --> getRoleLoggedUser(HttpServletRequest request)
  - get email --> getEmailLoggedUser(HttpServletRequest request)
