package com.nakahama.simpenbackend.Payroll.dto.Fee;

import java.util.List;

import com.nakahama.simpenbackend.Kelas.dto.Program.ReadProgram;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReadFeeGrouped {
    private ReadProgram program;

    private List<ReadFee> ListFee;
}
