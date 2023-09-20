package com.logicgate.payrollmanagement.image.repository;

import com.logicgate.payrollmanagement.image.model.Picture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PictureRepository extends JpaRepository<Picture, Long> {
}
