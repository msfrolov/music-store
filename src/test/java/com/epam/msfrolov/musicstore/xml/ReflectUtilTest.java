package com.epam.msfrolov.musicstore.xml;

import com.epam.msfrolov.musicstore.model.BaseEntity;
import com.epam.msfrolov.musicstore.model.Track;
import com.epam.msfrolov.musicstore.util.ReflectUtil;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ReflectUtilTest {

    @Test
    public void testCheckPresenceField() throws Exception {
        assertTrue(ReflectUtil.checkField("duration", Track.class));
    }

    @Test
    public void testGetAllField() throws Exception {
        List<Field> allField = ReflectUtil.getAllFields(Track.class);
        assertEquals(10, allField.size());
    }

    @Test
    public void testGetAllSuperClasses() throws Exception {
        List<Class> allSuperClasses = ReflectUtil.getAllSuperClasses(Track.class);
        //Last class in list should be equals BaseEntity
        Class clazz = allSuperClasses.get(allSuperClasses.size() - 1);
        assertEquals(BaseEntity.class, clazz);
    }
}